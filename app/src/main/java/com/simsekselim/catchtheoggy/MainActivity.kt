package com.simsekselim.catchtheoggy



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random as Random

var score = 0
var imageArray = ArrayList<ImageView>()
var handler = Handler(Looper.getMainLooper())
var runnable = Runnable {  }

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        hideImages()

        //CountDown Timer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                textTime.text = "Time : ${p0/1000}"

            }

            override fun onFinish() {

                textTime.text = "Time : 0"

                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Your Score : $score")

                alert.setPositiveButton("Yes"){dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)




                }
                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()

                }
            }.start()
        }

    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {

                for(image in imageArray){

                    image.visibility = ImageView.INVISIBLE

                }

                val random = java.util.Random()
                val randomIndex = Random.nextInt(12)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,250)
            }


        }

            handler.post(runnable)
        


    }
    fun increaseScore(view : View) {
        score = score + 1
        textScore.text = "Score : $score"
    }
}












