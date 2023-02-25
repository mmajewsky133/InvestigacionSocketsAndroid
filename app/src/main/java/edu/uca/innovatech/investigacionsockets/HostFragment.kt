package edu.uca.innovatech.investigacionsockets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uca.innovatech.investigacionsockets.databinding.FragmentHostBinding
import java.net.InetSocketAddress
import java.net.ServerSocket
import kotlin.concurrent.thread

class HostFragment : Fragment() {

    //el view binding
    private var _binding: FragmentHostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var message = ""

        thread {
            val server = ServerSocket()
            server.reuseAddress = true
            server.bind(InetSocketAddress(12345))

            while(!server.isClosed){
                val socket = server.accept()
                message = String(socket.getInputStream().readBytes())
                println(message)
                socket.close()

                activity?.runOnUiThread(java.lang.Runnable {
                    binding.tvSocketText.text = message
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}