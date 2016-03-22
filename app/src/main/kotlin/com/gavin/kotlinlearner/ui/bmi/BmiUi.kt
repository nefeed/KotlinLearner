package com.gavin.kotlinlearner.ui.bmi

import android.text.InputType.TYPE_CLASS_NUMBER
import android.view.Gravity
import android.widget.*
import com.gavin.kotlinlearner.R.color.colorTextNine
import com.gavin.kotlinlearner.R.string.*
import com.gavin.kotlinlearner.widget.bmiLogoView
import org.jetbrains.anko.*

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-03-22
 * Time: 11:27
 */
class BmiUi : AnkoComponent<BMIActivity> {

    var mTvAdvice :TextView ?= null
    var mEtHeight :EditText ?= null
    var mEtWeight :EditText ?= null
    var mRgSex :RadioGroup ?= null
    var mRbSexMan :RadioButton ?= null
    var mRbSexWoMan :RadioButton ?= null
    var mSex :Int = -1

    private val customeStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 16f
            is EditText -> v.textSize = 14f
            is TextView -> v.textSize = 14f
        }
    }

    override fun createView(ui: AnkoContext<BMIActivity>) = with(ui) {
        verticalLayout {
            padding = dip(16)

            bmiLogoView()

            linearLayout {

                matchParent

                textView {
                    textResource = bmi_height
                }

                mEtHeight = editText {
                    hintResource = bmi_hint_height
                    inputType = TYPE_CLASS_NUMBER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }

            }.lparams {
                height = wrapContent
                width = matchParent
            }

            linearLayout {

                matchParent

                textView {
                    textResource = bmi_weight
                }

                mEtWeight = editText {
                    hintResource = bmi_hint_weight
                    inputType = TYPE_CLASS_NUMBER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                }

            }.lparams {
                height = wrapContent
                width = matchParent
            }

            mRgSex = radioGroup {

                orientation = RadioGroup.HORIZONTAL

                mRbSexMan = radioButton {
                    textResource = sex_woman
                }

                mRbSexWoMan = radioButton {
                    textResource = sex_man
                }

                onCheckedChange { radioGroup, i ->
                    mSex = i - 1
                    println("您选中了：" + mSex)
                }

            }.lparams {
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }

            button(bmi_calculate) {
                onClick {
                    var _height :Float = (mEtHeight?.text.toString().toFloat() / 100)
                    var _weight :Float = mEtWeight?.text.toString().toFloat()
                    var _advice = ui.owner.calculateBmi(ui, _height, _weight, mSex)
                    mTvAdvice?.text = _advice
                }
            }

            mTvAdvice = textView {
                padding = dip(10)
                gravity = Gravity.CENTER_HORIZONTAL
                textResource = bmi_advice
                textColor = colorTextNine
            }

        }
    }

}