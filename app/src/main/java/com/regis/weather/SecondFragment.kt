package com.regis.weather

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.ImageLoader
import coil.load
import com.android.volley.toolbox.ImageRequest
import com.regis.weather.api.ApiService
import com.regis.weather.app.WeatherApp
import com.regis.weather.databinding.FragmentSecondBinding
import com.regis.weather.model.Weather
import com.regis.weather.viewModel.DbViewModel
import com.regis.weather.viewModel.DbViewModelFactory
import com.regis.weather.viewModel.WeatherViewModel
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()
    private var _context: Context? = null



    override fun onAttach(context: Context) {
        _context = context;
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refresh.setOnClickListener {
            getData();
        }

        getData()

    }

    @OptIn(DelicateCoroutinesApi::class)
fun getData() {
        val quotesApi = RetrofitHelper.getInstance().create(ApiService::class.java)
        // launching a new coroutine

        GlobalScope.launch {
            try {

                withContext(Dispatchers.Main) {
                    binding.info.text = "Getting weather info..."
                    binding.refresh.visibility = View.GONE
                    binding.progress.visibility = View.VISIBLE
                }

                val lat=-1.956128
                val lon=30.065686

                val result = quotesApi.getWeather(lat,lon)
                val weather = result.body()

                withContext(Dispatchers.Main) {
                    setWeather(weather);
                }

            } catch (e: Exception) {
                println("ERROR ${e.localizedMessage}")

                withContext(Dispatchers.Main) {
                    binding.dataLayout.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    binding.loaderLayout.visibility = View.VISIBLE
                    binding.info.text = e.message
                    binding.refresh.visibility = View.VISIBLE
                }

            }
        }

    }

    private fun setWeather(weather: Weather?) {
        binding.dataLayout.visibility = View.VISIBLE
        binding.loaderLayout.visibility = View.GONE

        binding.textviewCity.text = weather!!.cityName
        binding.textviewWeather.text = weather.subWeather[0].description
        binding.textviewDegrees.text = "${weather.main.temperature.toInt()}°c"
        binding.textviewMin.text = "${weather.main.minTemperature.toInt()}°c"
        binding.textviewMax.text = "${weather.main.maxTemperature.toInt()}°c"

        binding.textviewPressure.text = "${weather.main.pressure.toInt()} hPa"
        binding.textviewHumidity.text = "${weather.main.humidity.toInt()}%"
        binding.textviewWind.text = "${weather.wind.speed} m/s"

        val iconImage:ImageView=binding.icon;
        iconImage.load("https://openweathermap.org/img/wn/${weather.subWeather[0].icon}@4x.png")

        val df = SimpleDateFormat("MMM dd yyyy HH:mm")
        val dt = Date(weather.dt * 1000)
        binding.sync.text="Last sync: ${df.format(dt)}";
    }

    private fun getData1() {
         val dbViewModel: DbViewModel by viewModels {
            DbViewModelFactory((activity?.application as WeatherApp).repository)
        }

        dbViewModel.allRecords.observe(viewLifecycleOwner) { weather ->
            // Update the cached copy of the words in the adapter.
          println("CHEEEECCCCCKKKKKK")
            println(weather)
        }

//        viewModel.weatherResponse.observe(requireActivity()) { weather ->
//
//            binding.apply {
//
//                println(weather.toString())
//
//            }
//
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}