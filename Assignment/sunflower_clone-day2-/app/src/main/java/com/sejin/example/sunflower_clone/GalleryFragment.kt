package com.sejin.example.sunflower_clone

import GalleryViewModel
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sejin.example.sunflower_clone.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    val args: PlantDetailFragmentArgs by navArgs()

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        viewModel.photos.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "${it.size}개 받았습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}