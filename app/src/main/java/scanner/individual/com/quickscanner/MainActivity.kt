package scanner.individual.com.quickscanner

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<View>(R.id.button) as Button
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val myImageView = findViewById<View>(R.id.imgview) as ImageView
                val txtView = findViewById<View>(R.id.txtContent) as TextView
                val myBitmap = BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.example)
                myImageView.setImageBitmap(myBitmap)
                val detector = BarcodeDetector.Builder(applicationContext)
                        .setBarcodeFormats(Barcode.DATA_MATRIX or Barcode.QR_CODE)
                        .build()
                if (!detector.isOperational) {
                    txtView.setText("Could not set up the detector!")
                    return
                }else {
                    val frame = Frame.Builder().setBitmap(myBitmap).build()
                    val barcodes = detector.detect(frame)
                    val thisCode = barcodes.valueAt(0)
                    val txtView = findViewById<View>(R.id.txtContent) as TextView
                    txtView.text = thisCode.rawValue
                }
            }
        })
    }
}
