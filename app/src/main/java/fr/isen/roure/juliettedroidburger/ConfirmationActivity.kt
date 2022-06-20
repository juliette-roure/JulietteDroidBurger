package fr.isen.roure.juliettedroidburger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.roure.juliettedroidburger.databinding.ActivityConfirmationBinding
import fr.isen.roure.juliettedroidburger.databinding.ActivityMainBinding

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = this.getSharedPreferences("SharedStorage", Context.MODE_PRIVATE)
        val address = intent.getStringExtra("address")
        val burger = intent.getStringExtra("burger")
        val time = intent.getStringExtra("time")
        val name = preferences.getString("name","noname")
        val surname = preferences.getString("surname","noname")

        Log.d("TOUTESTLA:", address+burger+time+name+surname)

        binding.personTextView.text = "$surname $name"
        binding.addressTextView.text = address
        binding.commandTextView.text = "$burger livré à $time"

    }
}