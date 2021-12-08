package uz.gita.banking.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.banking.R
import uz.gita.banking.databinding.PageMainBinding
import uz.gita.banking.utils.scope

class MainPage :Fragment(R.layout.page_main){

    private val binding by viewBinding(PageMainBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

    }
}