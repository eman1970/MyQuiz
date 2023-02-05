package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    var quantity = 0
    var questionNo = 0
    var statementsArray = ArrayList<String>()
    var correctStatementsArray = ArrayList<String>()
    var rightAnswersArray = listOf<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation", "NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        }

        viewModel?.products?.observe(viewLifecycleOwner) { products ->

            var count=0
            for (i in 0 until 8) {
                correctStatementsArray.add(products[i].correctStatement)
                count++
            }

            statementsArray.add(0,products[0].statement)
            statementsArray.add(1,products[1].statement)
            statementsArray.add(2,products[2].statement)
            statementsArray.add(3,products[3].statement)
            statementsArray.add(4,products[4].statement)
            statementsArray.add(5,products[5].statement)
            statementsArray.add(6,products[6].statement)
            statementsArray.add(7,products[7].statement)
            statementsArray.add("Din poäng:")

            rightAnswersArray = listOf<Boolean>(products[0].isCorrect, products[1].isCorrect,products[2].isCorrect,products[3].isCorrect,products[4].isCorrect,products[5].isCorrect,products[6].isCorrect,products[7].isCorrect)

            binding.textStatement.text = products[0].statement

            binding.buttonPlus.setOnClickListener {
                if (questionNo<8)
                    showToast(true)
                if (questionNo==8)
                   binding.textAntal.setVisibility(View.VISIBLE);
                return@setOnClickListener
            }

            binding.buttonMinus.setOnClickListener {
                if (questionNo<8)
                    showToast(false)
                if (questionNo==8)
                   binding.textAntal.setVisibility(View.VISIBLE);
                return@setOnClickListener
            }

        }

        binding.textStatement.setVisibility(View.INVISIBLE);
        binding.textAntal.setVisibility(View.INVISIBLE);
        binding.buttonPlus.setVisibility(View.GONE);
        binding.buttonMinus.setVisibility(View.GONE);

        binding.button.setOnClickListener {
            binding.button.setVisibility(View.GONE);
            binding.textInfo.setVisibility(View.INVISIBLE);
            binding.textStatement.setVisibility(View.VISIBLE);
            binding.buttonPlus.setVisibility(View.VISIBLE);
            binding.buttonMinus.setVisibility(View.VISIBLE);
        }

        viewModel?.quantity?.observe(viewLifecycleOwner) { updateTotalCount(it) }

    }

    private fun updateTotalCount(it: Int) {
        binding.textAntal.text = it.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showToast(answer: Boolean) {
        if (answer==rightAnswersArray.get(questionNo)) {
            Toast.makeText(context?.applicationContext, "Rätt!", Toast.LENGTH_SHORT).show()
            updateQuestion()
            updateQuantity()
        }
        else if (answer==false) {
            Toast.makeText(context?.applicationContext, "Fel! Detta påstående är faktiskt sant", Toast.LENGTH_SHORT).show()
            updateQuestion()
        }
        else  {
            Toast.makeText(context?.applicationContext, correctStatementsArray.get(questionNo), Toast.LENGTH_SHORT).show()
            updateQuestion()
        }
    }

    fun updateQuestion() {
        questionNo = questionNo + 1
        binding.textStatement.setText(statementsArray.get(questionNo))
    }

    fun updateQuantity() {
        quantity = quantity + 1
        binding.textAntal.setText(quantity.toString())

    }


}











