package uz.gita.banking.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.banking.R
import uz.gita.banking.databinding.PageServicesBinding
import uz.gita.banking.databinding.PageTransferBinding

class TransferPage:Fragment(R.layout.page_transfer) {

    private val binding by viewBinding(PageTransferBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}