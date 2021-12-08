package uz.gita.banking.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.banking.R
import uz.gita.banking.databinding.PagePaymentBinding
import uz.gita.banking.databinding.PageServicesBinding
import uz.gita.banking.utils.scope

class ServicePage:Fragment(R.layout.page_services) {
    private val binding by viewBinding(PageServicesBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

    }
}