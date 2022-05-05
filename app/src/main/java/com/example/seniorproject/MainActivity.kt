package com.example.seniorproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.seniorproject.R.id.*
import java.math.RoundingMode
import java.text.DecimalFormat



class MainActivity : AppCompatActivity() {


    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val maxHP = 1.0
        var currentHP = 0.0
        val grass = 1.0
        var catchRate = 0.0
        var ball: Double
        val levelM = 1.0
        var status = 0.0
        val diff = 1.0
        var selectedBall = ""
        var pkmon = ""
        var statEffect = ""
        val balls = resources.getStringArray(R.array.ListofPokeBalls)
        val poke = resources.getStringArray(R.array.Pokemon)
        val statusEffect = resources.getStringArray(R.array.StatusEffect)

        val pokemon = findViewById<Spinner>(PKspinner)
        if (pokemon != null) {
            val adapt = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, poke)
            pokemon.adapter = adapt

            pokemon.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    pkmon = poke[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity, "Nothing Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
       val pokeb = findViewById<Spinner>(Pokeball)
        if (pokeb != null) {
            val adapt = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, balls)
            pokeb.adapter = adapt

            pokeb.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    selectedBall = balls[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity, "Nothing Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val statusEff = findViewById<Spinner>(StatusEffect)
        if (statusEff != null) {
            val adapt = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statusEffect)
            statusEff.adapter = adapt

            statusEff.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    statEffect = statusEffect[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity, "Nothing Selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
/*
####################################################################
******************************CALCULATOR****************************
####################################################################
 */


        val calc: Button = findViewById(Calc)
       calc.setOnClickListener{


/*
####################################################################
******************************Variables****************************
####################################################################
 */

        val cHP = findViewById<EditText>(PercentofHealth).text.toString().toDoubleOrNull()
           if (cHP != null) {
               currentHP = cHP.div(100.0)
           }

        val tarlvl = findViewById<EditText>(TargetLVL).text.toString().toDoubleOrNull()
        val playlvl = findViewById<EditText>(playLVL).text.toString().toDoubleOrNull()

        val turNum = findViewById<EditText>(TurnNum).text.toString().toDoubleOrNull()


/*
####################################################################
******************************Variables****************************
####################################################################
 */
/*
####################################################################
******************************BALLS********************************
####################################################################
 */
        if(selectedBall == "PokeBall" || selectedBall == "PremierBall" || selectedBall == "HealBall" || selectedBall == "FriendBall" || selectedBall == "LuxuryBall" ){
            ball = 1.0
        }
           if(selectedBall == "GreatBall"){
               ball = 1.5
           }
           if(selectedBall == "UltraBall"){
               ball = 2.0
           }


            val sw1 = findViewById<Switch>(Fishing)
            sw1.setOnClickListener {
                if (sw1.isChecked) {
                    if (selectedBall == "LureBall") {
                        ball = 4.0
                    }
                    if(selectedBall == "DiveBall"){
                        ball = 3.5
                    }

                }

            }

           ball = if(selectedBall == "DreamBall" && statEffect == "Asleep") {
               4.0
           } else{
               1.0
           }

            if(selectedBall == "QuickBall" ) {
                ball = if (turNum == 1.0) {
                    5.0

                } else {
                    1.0
                }
            }

            if(selectedBall == "TimerBall")
            {

                val tempBall = 1.0 + (turNum?.times((0.3))!!)
                ball = if (tempBall <= 4) {
                    tempBall
                } else {
                    4.0
                }

            }

            val sw3 = findViewById<Switch>(NightCave)
            sw3.setOnClickListener{
                if (sw1.isChecked) {
                    if (selectedBall == "DuskBall") {
                        ball = 3.0
                    }
                }
            }
            val sw4 = findViewById<Switch>(Repeat)
            sw4.setOnClickListener {
                if (sw1.isChecked) {
                    if (selectedBall == "RepeatBall") {
                        ball = 3.5
                    }
                }
            }

            if(selectedBall == "Nestball"){
                if (tarlvl != null) {
                    ball = if(tarlvl < 31 ){
                        (41-tarlvl)/10
                    } else{
                        1.0
                    }

                }
            }
            if(selectedBall == "LevelBall"){
                if (playlvl != null && tarlvl != null) {
                    if(tarlvl < (playlvl/4.0)){
                        ball = 8.0
                    }
                    if( tarlvl < (playlvl/2.0)){
                        ball = 4.0
                    }
                    if( tarlvl < playlvl){
                        ball = 2.0
                    }
                }
                else{
                   ball = 1.0
                }
            }
/*
####################################################################
******************************BALLS********************************
####################################################################
 */
 /*
####################################################################
******************************STATUSEFFECT**************************
####################################################################
 */
        if(statEffect == "Asleep" || statEffect == "Frozen"){
            status = 2.5
        }
        if(statEffect == "Poisoned" || statEffect == "Paralyzed" || statEffect == "Burned"){
            status = 1.5
        }
        if(statEffect == "None"){
            status = 1.0
        }
 /*
####################################################################
******************************STATUSEFFECT**************************
####################################################################
 */

/*
####################################################################
******************************POKEMON*******************************
####################################################################
 */

       if(pkmon == "Basculin") {
            catchRate = 25.0
       }
       if(pkmon == "Aegislash" || pkmon == "Appletun" || pkmon == "Arctovish" || pkmon == "Arctozolt" || pkmon == "Barbaracle" || pkmon == "Bellossom" || pkmon == "Bisharp" || pkmon == "Boltund") {
               catchRate = 45.0
       }
       if(pkmon == "Avalugg") {
           catchRate = 55.0
       }
       if(pkmon == "Abomasnow" || pkmon == "Beartic" || pkmon == "Barraskewda") {
           catchRate = 60.0
       }
       if(pkmon == "Bewear") {
           catchRate = 70.0
       }
       if(pkmon == "Axew" || pkmon == "Arcanine" || pkmon == "Accelgor") {
           catchRate = 75.0
       }
       if(pkmon == "Beheeyem") {
           catchRate = 90.0
       }
       if(pkmon == "Alcremie" || pkmon == "Araquanid") {
           catchRate = 100.0
       }
       if(pkmon == "Binacle" || pkmon == "Boldore") {
           catchRate = 120.0
       }
       if(pkmon == "Aromatisse") {
           catchRate = 140.0
       }
       if(pkmon == "Barboach" || pkmon == "Bergmite") {
           catchRate = 190.0
       }
       if(pkmon == "Applin" || pkmon == "Arrokuda" || pkmon == "Baltoy" || pkmon == "Blipbug" || pkmon == "Bonsly") {
           catchRate = 255.0
       }

/*
####################################################################
******************************POKEMON*******************************
####################################################################
 */
/*
####################################################################
******************************TEST*******************************
####################################################################
 */
           /*Toast.makeText(
               this@MainActivity,
               "Ball: " + currentHP,
               Toast.LENGTH_SHORT
           ).show()*/
           if(currentHP != 0.0 ) {
               val catchChance =
                   (((((3 * maxHP) - (2 * currentHP)) * grass * catchRate * ball) / (3 * maxHP)) * levelM * status * diff) / 1.65
               val df = DecimalFormat("##.##")
               df.roundingMode = RoundingMode.CEILING
               val catch = df.format(catchChance)
               val string = findViewById<TextView>(calcChance)
               string.text = "You have roughly a $catch% to catch $pkmon."
           }
           else
           {
               val string = findViewById<TextView>(calcChance)
               string.text = "Error: Enter Data Please."
           }


/*
####################################################################
******************************TEST*******************************
####################################################################
 */
        }
    }
}

