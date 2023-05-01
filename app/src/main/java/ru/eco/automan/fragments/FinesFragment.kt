package ru.eco.automan.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eco.automan.databinding.FragmentFinesBinding
import java.time.LocalDateTime
import java.time.Month

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
        val view = binding.root

        with(binding) {
            checkButton.setOnClickListener {
                val url = "https://xn--90adear.xn--p1ai/check/fines"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

                val time = LocalDateTime.now()
                val year = time.year.toString()
                val month = getRuMonth(time)
                val day = time.dayOfMonth.toString()
                val hour = time.hour.toString()
                val minute = time.minute.toString()
                val str = "Проверка проведена $day $month $year г. в $hour:$minute"
                lastCheck.text = str
            }
        }
        return view
    }
}

/*
 * Функция, возвращающая текущий месяц на руском языке в родительском падеже
 */
fun getRuMonth(dataTime: LocalDateTime) : String {
    return when(dataTime.month) {
        Month.JANUARY -> "января"
        Month.FEBRUARY -> "февраля"
        Month.MARCH -> "марта"
        Month.APRIL -> "апреля"
        Month.MAY -> "мая"
        Month.JUNE -> "июня"
        Month.JULY -> "июля"
        Month.AUGUST -> "августа"
        Month.SEPTEMBER -> "сентября"
        Month.OCTOBER -> "октября"
        Month.NOVEMBER -> "ноября"
        Month.DECEMBER -> "декабря"
        else -> "ERROR"
    }
}
