package kr.ac.hallym.prac11_provider

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import kr.ac.hallym.prac11_provider.databinding.ActivityMainBinding
import java.io.File
import java.lang.Exception
import java.security.cert.CertPath
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var filePath: String

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )

                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio

                var inputStream =contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                bitmap?.let{
                    binding.userImageView.setImageBitmap(bitmap)
                }?: let{
                    Log.d("week14", "bitmap null")
                }
            } catch (e:Exception) {
                Log.d("week14", "bitmap null")
            }
        }

        binding.galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio

            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let{
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir:File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
            filePath = file.absolutePath
            val photoURI = FileProvider.getUriForFile(this, "kr.ac.hallym.prac11_provider.fileprovider", file)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)
        }
    }

    private fun calculateInSampleSize(fileUri:Uri, reqWidth: Int, reqHeight: Int) : Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try{
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e:Exception) {
            e.printStackTrace()
        }

        val (height: Int, width : Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if(height > reqHeight || width>reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width/2
            while(halfHeight / inSampleSize >= reqHeight &&
                    halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}