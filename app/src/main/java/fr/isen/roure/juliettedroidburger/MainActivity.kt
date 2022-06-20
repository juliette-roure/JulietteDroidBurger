package fr.isen.roure.juliettedroidburger

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.roure.juliettedroidburger.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                binding.timeTextView.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true).show()
        }
        var errorInOrdering = false
        binding.validationButton.setOnClickListener {
            if(binding.nameText.text.isEmpty()){
                binding.nameText.setHintTextColor(Color.RED)
                errorInOrdering = true
            }
            if(binding.surnameText.text.isEmpty()){
                binding.surnameText.setHintTextColor(Color.RED)
                errorInOrdering = true
            }
            if(binding.phoneText.text.isEmpty()){
                binding.phoneText.setHintTextColor(Color.RED)
                errorInOrdering = true
            }
            if(binding.addressText.text.isEmpty()){
                binding.addressText.setHintTextColor(Color.RED)
                errorInOrdering = true
            }
            if(errorInOrdering){
                Toast.makeText(this,"Erreur dans les champs en rouge",Toast.LENGTH_SHORT).show()
                errorInOrdering=false
            }else{
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("address",binding.addressText.text.toString())
                intent.putExtra("burger",binding.burgerSpinner.selectedItem.toString())
                intent.putExtra("time",binding.timeTextView.text)

                val preferences = this.getSharedPreferences("SharedStorage", Context.MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("name", binding.nameText.text.toString())
                editor.putString("surname", binding.surnameText.text.toString())
                editor.commit()

                startActivity(intent)
            }

        }


    }
}