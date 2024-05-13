package com.example.mlextracttextapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mlextracttextapp.databinding.ActivityMainBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

const val REQUEST_CODE = 123

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var etResult: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etResult = findViewById(R.id.etResult)

        binding.btnCamera.setOnClickListener {
            // open up the camera and store the image
            // run the ML algo on the image to extract the text out of it

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (intent.resolveActivity(packageManager) != null) {
                // to receive the image and send it for result extraction
                startActivityForResult(intent, REQUEST_CODE)

            } else {
                // something went wrong
                Toast
                    .makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnErase.setOnClickListener {
            etResult.setText("")
        }

        binding.btnCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Extracted Text", etResult.text)
            clipboard.setPrimaryClip(clip)

            Toast
                .makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT)
                .show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val extras = data?.extras
            val bitmap = extras?.get("data") as Bitmap
            detectTextUsingML(bitmap)
        }
    }

    private fun detectTextUsingML(bitmap: Bitmap) {
        // When using Latin script library
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromBitmap(bitmap, 0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                etResult.setText(visionText.text)
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Toast
                    .makeText(this, "Oops, something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}