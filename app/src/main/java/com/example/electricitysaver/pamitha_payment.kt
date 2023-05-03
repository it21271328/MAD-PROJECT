package com.example.electricitysaver

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_pamitha_payment2.*


class pamitha_payment : AppCompatActivity() {

    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    lateinit var adapter: SimpleCursorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pamitha_payment2)

        var helper = paymentHelper(applicationContext)
        db = helper.readableDatabase

        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor =
                Color.parseColor("#133B5C") // Replace with your desired color
        }
        val favourites= findViewById<Button>(R.id.viewFavourites)
        favourites.setOnClickListener {

            val mainIntent = Intent(this@pamitha_payment, Favourites::class.java)
            startActivity(mainIntent)
            //finish()

        }

        AddFav.setOnClickListener {
            var cv = ContentValues()
            cv.put("PAYEE",payeeCat.selectedItem.toString())
            cv.put("NAME",ptAccountName.text.toString())
            cv.put("ACCOUNT",ActNumber.text.toString())
            cv.put("AMOUNT",etAmount.text.toString())
            db.insert("PAYMENT",null,cv)

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Add To Favourite")
            ad.setMessage("Record Added to Favourite List Successflly ....!")
            ad.setPositiveButton("OK", DialogInterface.OnClickListener{ dialog: DialogInterface?, i->
            })
            ad.show()
        }
    }
}