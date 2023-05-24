package ru.eco.automan.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ru.eco.automan.R
import ru.eco.automan.databinding.FragmentFinesBinding


/*
 * Фрагмент для проверки штрафов на авто
 */
class FinesFragment : Fragment() {
    private lateinit var binding: FragmentFinesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinesBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
//            checkButton.setOnClickListener {
            val url = "https://clck.ru/FWbqt"
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(url)
//                startActivity(intent)
                webPage.webViewClient = WebViewClient()
                webPage.settings.javaScriptEnabled = true
                webPage.loadUrl(url)
                webPage.visibility = View.VISIBLE
//            } else {
//                noInternet.text = requireContext().resources.getString(R.string.no_internet)
//                noInternet.visibility = View.VISIBLE
//            }
//            checkButton.visibility = View.GONE
//            val time = LocalDateTime.now()
//            val year = time.year.toString()
//            val month = getRuMonth(time, requireContext())
//            val day = time.dayOfMonth.toString()
//            val hour = time.hour.toString()
//            val minute = time.minute.toString()
//            val str = "Проверка проведена $day $month $year г. в $hour:$minute"
//            lastCheck.text = str
        }
    }
}

