package com.example.animatedemo4

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var layoutW:Float = 0f
    var layoutH:Float = 0f
    var xBtnYes:Float = 0f
    var yBtnYes:Float = 0f
    var xBtnNo:Float = 0f
    var yBtnNo:Float = 0f
    var count:Int = 0
    final val TAG = "DEMO"
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgMain1.alpha=0f
        imgRocket.alpha=0f
//        txtAsk.setOnClickListener{
//            Toast.makeText(this,""+layoutW+" - "+layoutH+" / "+xBtnNo+" - "+yBtnNo+" / "
//                    +xBtnYes+" - "+yBtnYes,Toast.LENGTH_LONG).show()
//        }

        btnYes.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

        btnNo.setOnClickListener{
            count++
            if(count==7) {
                callRocket()
            }else {
                var rd = Random.nextInt(1, 9)
                //txtAsk.setText(""+rd)
                if (rd < 4)
                    swapBtn()
                else if (rd < 7)
                    putDown()
                else
                    putUp()
            }
        }

//        btnTest1.setOnClickListener{
//            var kq = Math.asin(1.0)
//            Toast.makeText(this,"kq: "+kq+" - "+kq*180/Math.PI,Toast.LENGTH_LONG).show()
//        }
//
//        btnTest2.setOnClickListener{
//            imgRocket.pivotX=50f
//            imgRocket.pivotY=50f
//            var a = layoutMain.height - (imgRocket.height/2) - btnNo.x - (btnNo.height/2)
//            var b = btnNo.x + (btnNo.width/2) - imgRocket.x - imgRocket.width/2
//            var goc = Math.atan(a/b.toDouble())*180/Math.PI
//            Toast.makeText(this,"a: "+a+"/ b: "+b+"kq: "+goc,Toast.LENGTH_LONG).show()
//            imgRocket.animate().y(imgRocket.y + imgRocket.height).setDuration(10)
//                .withEndAction {
//                    imgRocket.animate().y(imgRocket.y - imgRocket.height*2).alpha(1.0f).setDuration(1000)
//                        .withEndAction {
//                            imgRocket.animate().rotation(((90f - goc).toFloat())).setDuration(1000)
//                                .withEndAction {
//                                    imgRocket.animate().x(btnNo.x+btnNo.width/2-imgRocket.width/2)
//                                        .y(btnNo.y+btnNo.height/2).setDuration(1000)
//                                        .setInterpolator(AnticipateInterpolator()).withEndAction {
//                                            showBoom1()
//                                            imgMain1.animate().alpha(0f).setDuration(500)
//                                            btnNo.animate().y((layoutMain.height+btnNo.height).toFloat()).setStartDelay(100).setDuration(2000)
//                                                .withStartAction {
//                                                    btnNo.alpha=1f
//                                                    imgRocket.alpha=0f
//                                                    btnNo.animate().alphaBy(1f).alpha(0.2f).setDuration(2000)
//                                                }
//                                        }
//                                }
//                        }
//                }
//        }
//
//        btnTest3.setOnClickListener{
////            swapBtn()
////            imgMain1.pivotX=50f
////            imgMain1.pivotY=50f
//            //imgMain1.rotation=30f
//            imgMain1.animate().x(btnNo.x+btnNo.width/2-imgMain1.width/2)
//                .y(btnNo.y+btnNo.height/2-imgMain1.width/2).setDuration(100)
//                .withStartAction{
//                    btnNo.animate().alpha(0f).setDuration(100)
//                        .withEndAction {
//                            imgMain1.animate().alpha(0f).setDuration(300).setStartDelay(500)
//                            btnNo.animate().alpha(0.8f).setDuration(300).setStartDelay(500)
//
//                        }
//                }
//        }
//
//        btnTest4.setOnClickListener{
//            var temp = Random.nextInt(1,3)
//            if(temp == 1)
//                putUp()
//            else
//                putDown()
//        }

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun showBoom(){
        imgMain1.visibility = View.VISIBLE
        imgMain1.animate().x(btnNo.x+btnNo.width/2-imgMain1.width/2)
            .y(btnNo.y+btnNo.height/2-imgMain1.width/2).setDuration(1).setStartDelay(50)
            .withStartAction{
                btnNo.alpha = 0f
            }
    }

    fun showBoom1(){
        imgMain1.alpha=1f
        imgMain1.x = btnNo.x+btnNo.width/2-imgMain1.width/2
        imgMain1.y = btnNo.y+btnNo.height/2-imgMain1.width/2
        btnNo.alpha = 0f
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun callRocket(){
        imgRocket.pivotX=50f
        imgRocket.pivotY=50f
        var a = layoutMain.height - (imgRocket.height/2) - btnNo.x - (btnNo.height/2)
        var b = btnNo.x + (btnNo.width/2) - imgRocket.x - imgRocket.width/2
        var goc = Math.abs(Math.atan(a/b.toDouble())*180/Math.PI)
        //abs de tinh 2 thuong hop buttonNo ben trai hay phai so voi rocket
        var gocQuay = 0.0
        if(btnNo.x>layoutMain.width/2)
            gocQuay = (90f - goc)   //buttonNo ben phai rocket
        else
            gocQuay = goc - 90f     //buttonNo ben trai rocket
        //Toast.makeText(this,"a: "+a+"/ b: "+b+"kq: "+goc,Toast.LENGTH_LONG).show()
        imgRocket.animate().y(imgRocket.y + imgRocket.height).setDuration(10)
            .withEndAction {
                imgRocket.animate().y(imgRocket.y - imgRocket.height*2).alpha(1.0f).setDuration(1000)
                    .withEndAction {
                        imgRocket.animate().rotation(((gocQuay).toFloat())).setDuration(1000)
                            .withEndAction {
                                imgRocket.animate().x(btnNo.x+btnNo.width/2-imgRocket.width/2)
                                    .y(btnNo.y+btnNo.height/2).setDuration(1000)
                                    .setInterpolator(AnticipateInterpolator()).withEndAction {
                                        showBoom1()
                                        imgMain1.animate().alpha(0f).setDuration(500)
                                        btnNo.animate().y((layoutMain.height+btnNo.height).toFloat()).setStartDelay(100).setDuration(1000)
                                            .withStartAction {
                                                btnNo.alpha=1f
                                                imgRocket.alpha=0f
                                                btnNo.animate().alphaBy(1f).alpha(0.2f).setDuration(2000)
                                            }
                                    }
                            }
                    }
            }
    }

    fun getSize(){
        var dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        layoutW = dm.widthPixels.toFloat()
        layoutH = dm.heightPixels.toFloat()
        yBtnNo = btnNo.top.toFloat()
        xBtnNo = btnNo.left.toFloat()
        yBtnYes = btnYes.top.toFloat()
        xBtnYes = btnYes.left.toFloat()

    }

    fun swapBtn(){
        var posXYes = btnYes.x
        var posYYes = btnYes.y
        btnYes.animate().x(btnNo.x).y(btnNo.y).setDuration(200).start()
        btnNo.animate().x(posXYes).y(posYYes).setDuration(200).start()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //Toast.makeText(this,"onWindwonFocusChanged",Toast.LENGTH_SHORT).show()
        getSize()
    }

    fun getPos(){
        var xYes = btnYes.x
        var yYes = btnYes.y
        var wYes = btnYes.width
        var hYes = btnYes.height
        var wLayout = layoutMain.width
        var hLayout = layoutMain.height
        while(true){
            var check=1
            var tempX = Random.nextInt(0,wLayout-wYes)
            var tempY = Random.nextInt(713,1200)
            if(xYes<yYes){
                tempX
            }

            Log.i(TAG,""+tempX+" - "+tempY)
            if((tempX > xYes && tempX < (xYes+wYes) ) || (tempY > yYes && tempY < (yYes+ hYes)))
                check=0
            else {
                Log.i(TAG, "ok")
                btnNo.animate().x(tempX.toFloat()).y(tempY.toFloat()).setDuration(200)
            }
            if(check==1)
                break
        }
        Toast.makeText(this,"Done "+yYes,Toast.LENGTH_LONG).show()

    }

    fun putDown(){
        if(btnNo.y+btnNo.height > 1200)
            btnNo.animate().y(btnNo.y-btnNo.height).setDuration(200)
        else{
            btnNo.animate().y(btnNo.y+btnNo.height).setDuration(200)
        }
    }

    fun putUp(){
        if(btnNo.y-btnNo.height < 713)
            btnNo.animate().y(btnNo.y+btnNo.height).setDuration(200)
        else{
            btnNo.animate().y(btnNo.y-btnNo.height).setDuration(200)
        }
    }
}
