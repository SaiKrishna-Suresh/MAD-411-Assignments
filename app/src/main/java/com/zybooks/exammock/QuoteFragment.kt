package com.zybooks.exammock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.zybooks.exammock.network.RetrofitInstance
import kotlinx.coroutines.launch

class QuoteFragment :Fragment(){
    private lateinit var quoteText:TextView
    private lateinit var authorText:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteText = view.findViewById(R.id.quoteText)
        authorText = view.findViewById(R.id.authorText)
        val refreshQuoteButton: Button = view.findViewById(R.id.refreshQuoteButton)

        fetchRandomQuote()

        refreshQuoteButton.setOnClickListener {
            fetchRandomQuote()
        }
    }

    private fun fetchRandomQuote(){
        lifecycleScope.launch {
            try{
                val quotes = RetrofitInstance.api.getQuotes()
                val randomQuote = quotes.random()
                quoteText.text = "\"${randomQuote.text}\""
                authorText.text = "- ${randomQuote.author ?: "Unknown"}"
            } catch (e: Exception) {
                quoteText.text = "Failed to load quote."
                authorText.text = ""
            }


        }
    }

}