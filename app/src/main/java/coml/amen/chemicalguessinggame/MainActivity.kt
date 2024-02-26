package coml.amen.chemicalguessinggame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    private val chemicalNames = mutableListOf<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val toolBarTitle: TextView = findViewById(R.id.toolbar_title)
        toolBarTitle.text = "Chemical Guessing Game"

        val myChemicalImage: ImageView = findViewById(R.id.imageView)
        myChemicalImage.setImageResource(R.drawable.bg)

        val guessText: EditText = findViewById(R.id.guessChemical)
        val addChemical: EditText = findViewById(R.id.addChemical)
        val guessBtn: Button = findViewById(R.id.guessButton)
        val addBtn: Button = findViewById(R.id.addButton)
        val message: TextView = findViewById(R.id.displaySuccess)

        addBtn.setOnClickListener {
            val newChemical = addChemical.text.toString().trim()
            if (newChemical.isNotEmpty()) {
                if (newChemical in chemicalNames) {
                    message.text = "Chemical '$newChemical' is already available."
                } else {
                    chemicalNames.add(newChemical)
                    message.text = "Chemical '$newChemical' added."
                }
                addChemical.text.clear()
            }
        }
        guessBtn.setOnClickListener {
            val guessChemical = guessText.text.toString().trim()
            if (chemicalNames.isNotEmpty() && guessChemical.isNotEmpty()) {
                val randomChemical = chemicalNames[Random.nextInt(chemicalNames.size)]
                if (guessChemical.equals(randomChemical, ignoreCase = true)) {
                    message.text = "Congratulations! You guessed it right. It was $randomChemical."
                } else {
                    message.text = "Sorry, wrong guess. The correct answer was $randomChemical"
                }
                guessText.text.clear()
            } else {
                message.text = "Please make sure to enter a guess and that the list is not empty"
            }
        }
    }
}