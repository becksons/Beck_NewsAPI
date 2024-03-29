import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.ArticleAdapter
import com.example.newsapp.ArticleModelFactory
import com.example.newsapp.ArticleViewModel

import androidx.navigation.fragment.findNavController
import com.example.newsapp.R

import com.example.newsapp.databinding.ListFragmentBinding

class ListFragment : Fragment() {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = checkNotNull(_binding)

    private lateinit var articleListAdapter: ArticleAdapter
    private lateinit var articleViewModel: ArticleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val articleModelFactory = ArticleModelFactory(requireContext())
        articleListAdapter = ArticleAdapter(fragment = this)
        articleViewModel = ViewModelProvider(requireActivity(), articleModelFactory)[(ArticleViewModel::class.java)] // Instantiate ViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.newsRecyclerView.adapter = articleListAdapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.fetchArticles("general") //default val

        articleViewModel.articles.observe(viewLifecycleOwner) { articles ->
            articleListAdapter.updateData(articles)
        }
        binding.general.setOnClickListener {

            articleViewModel.fetchArticles("general")

        }
        binding.sports.setOnClickListener {

            articleViewModel.fetchArticles(category = "sports")

        }

        binding.entertainment.setOnClickListener {

            articleViewModel.fetchArticles(category = "entertainment")

        }

        binding.business.setOnClickListener {

            articleViewModel.fetchArticles(category = "business")

        }
        binding.health.setOnClickListener {

            articleViewModel.fetchArticles(category = "health")

        }

        binding.science.setOnClickListener {

            articleViewModel.fetchArticles(category = "science")

        }


    }
    fun newsNavigation() {
        findNavController().navigate(R.id.list_to_detail)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}