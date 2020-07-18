package com.piiwpum.touchtechnologiestest.ui.main

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.piiwpum.touchtechnologiestest.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        setActionClick()
    }

    fun setActionClick() {
        btn_get?.setOnClickListener {
            context?.hideKeyboard(view!!)
            var pay = et_pay?.text.toString().trim()
            var price = et_price?.text.toString().trim()

            if (pay.isEmpty() || price.isEmpty()) {
                Toast.makeText(context, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show()
            } else if (pay.toInt() == 0 || price.toInt() == 0) {
                Toast.makeText(
                    context,
                    "กรุณากรอกราคาสิ้นค่า และ เงิน ให้มากกว่า 0",
                    Toast.LENGTH_LONG
                ).show()
            } else if (price.toInt() > pay.toInt()) {
                Toast.makeText(context, "เงินไม่พอชำระสิ้นค้า", Toast.LENGTH_LONG).show()
            } else {
                Log.d("test", "pay =${pay.toInt()}")
                Log.d("test", "price =${price.toInt()}")
                calculate(pay.toInt() - price.toInt())
            }

        }

    }


    fun calculate(chance: Int) {
        Log.d("test", "chance =${chance}")
        var result = chance
        var check = true
        var _500: Int = 0
        var _100: Int = 0
        var _50: Int = 0
        var _20: Int = 0
        var _10: Int = 0
        var _5: Int = 0
        var _1: Int = 0

        while (check) {
            if (result >= 500) {
                result -= 500
                _500++
            } else if (result >= 100) {
                result -= 100
                _100++

            } else if (result >= 50) {
                result -= 50
                _50++

            } else if (result >= 20) {
                result -= 20
                _20++

            } else if (result >= 10) {
                result -= 10
                _10++

            } else if (result >= 5) {
                result -= 5
                _5++

            } else if (result >= 1) {
                result -= 1
                _1++
            }

            if (result == 0) {
                check = false
                tv_chance?.setText("${chance}")
                tv_500?.setText("${_500}")
                tv_100?.setText("${_100}")
                tv_50?.setText("${_50}")
                tv_20?.setText("${_20}")
                tv_10?.setText("${_10}")
                tv_5?.setText("${_5}")
                tv_1?.setText("${_1}")

            }

        }
    }


    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}