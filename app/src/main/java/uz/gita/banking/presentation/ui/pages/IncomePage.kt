package uz.gita.banking.presentation.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.banking.R
import uz.gita.banking.databinding.PageIncomeBinding
import uz.gita.banking.utils.scope

class IncomePage :Fragment(R.layout.page_income) {
    private val binding by viewBinding(PageIncomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

    }
}