package edu.uca.innovatech.investigacionsockets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uca.innovatech.investigacionsockets.databinding.FragmentSocketBinding
import java.net.Socket
import kotlin.concurrent.thread

class SocketFragment : Fragment() {

    //el view binding
    private var _binding: FragmentSocketBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSocketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMandar.setOnClickListener(){
            val message = binding.etSocketText.text.toString()

            thread {
                val socket = Socket("192.168.1.7", 12345)
                socket.getOutputStream().write(message.toByteArray())
                socket.close()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}