package com.example.sportapp.ui.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sportapp.R
import com.example.sportapp.databinding.FragmentStoperBinding
import org.w3c.dom.Text
import java.util.*


class Stoper : Fragment(){

    lateinit var dataHelper: TimerData

    private val timer = Timer()

    //private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentStoperBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var buttonStart: Button? = null
    private var buttonReset: Button? = null
    private var showTime: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("STARTUJE", "################################################")
        _binding = FragmentStoperBinding.inflate(layoutInflater)
        //setContentView(new stoper(this))
        dataHelper = TimerData(requireContext())




        if(dataHelper.timerCounting())
        {
            startTimer()
        }
        else
        {
            stopTimer()
            if(dataHelper.startTime() != null && dataHelper.stopTime() != null)
            {
                val time = Date().time - calcRestartTime().time
                //showTime!!.text = timeStringFromLong(time)
            }
        }

        timer.scheduleAtFixedRate(TimeTask(), 0, 500)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stoper, container, false)

        buttonStart = view.findViewById<View>(R.id.startButton) as Button
        buttonReset = view.findViewById<View>(R.id.resetButton) as Button
        showTime = view.findViewById<View>(R.id.timeTV) as TextView
        buttonStart!!.setOnClickListener {
            Log.i("CLICK1", "################################################")
            startStopAction()

        }
        buttonReset!!.setOnClickListener {
            Log.i("CLICK2", "################################################")
            resetAction()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("CREATED", "################################################")
        binding.startButton.setOnClickListener{
            Log.i("CLICK1", "################################################")
            startStopAction()

        }
        binding.resetButton.setOnClickListener{
            Log.i("CLICK2", "################################################")
            resetAction()
        }
    }

    private inner class TimeTask: TimerTask()
    {
        override fun run()
        {
            if(dataHelper.timerCounting())
            {
                val time = Date().time - dataHelper.startTime()!!.time
                showTime!!.text = timeStringFromLong(time)
            }
        }
    }

    private fun resetAction()
    {
        dataHelper.setStopTime(null)
        dataHelper.setStartTime(null)
        stopTimer()
        showTime!!.text = timeStringFromLong(8)
    }

    private fun stopTimer()
    {
        dataHelper.setTimerCounting(false)
        binding.startButton.text = getString(R.string.reset)
        Log.i("Stop", "1111111111111111111111111111111111111")
    }

    private fun startTimer()
    {
        dataHelper.setTimerCounting(true)
        binding.startButton.text = getString(R.string.start)
        Log.i("Start", "222222222222222222222222222222222222")
    }

    private fun startStopAction()
    {
        if(dataHelper.timerCounting())
        {
            dataHelper.setStopTime(Date())
            stopTimer()
        }
        else
        {
            if(dataHelper.stopTime() != null)
            {
                dataHelper.setStartTime(calcRestartTime())
                dataHelper.setStopTime(null)
            }
            else
            {
                dataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun calcRestartTime(): Date
    {
        val diff = dataHelper.startTime()!!.time - dataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    private fun timeStringFromLong(ms: Long): String
    {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60) % 60)
        val hours = (ms / (1000 * 60 * 60) % 24)
        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String
    {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}