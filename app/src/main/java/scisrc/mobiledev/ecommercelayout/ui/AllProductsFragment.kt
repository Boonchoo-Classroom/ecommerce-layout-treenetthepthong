package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import scisrc.mobiledev.ecommercelayout.R
import scisrc.mobiledev.ecommercelayout.adapter.ProductAdapter
import scisrc.mobiledev.ecommercelayout.adapter.CategoryAdapter
import scisrc.mobiledev.ecommercelayout.databinding.FragmentAllProductsBinding
import scisrc.mobiledev.ecommercelayout.model.Product

class AllProductsFragment : Fragment() {
    private var _binding: FragmentAllProductsBinding? = null
    private val binding get() = _binding!!

    private val allProducts = listOf(
        Product("คุกกี้ธัญพืช", 20.0, "คุกกี้ธัญพืช", R.drawable.cookie1, "Cookie"),
        Product("คุกกี้ช็อคโกแลต", 20.0, "คุกกี้ช็อคโกแลต", R.drawable.cookie2, "Cookie"),
        Product("คุกกี้รสสตอเบอร์รี่", 20.0, "คุกกี้รสสตอเบอร์รี่", R.drawable.cookies3, "Cookie"),
        Product("คุกกี้รสหลากสี", 20.0, "คุกกี้รสหลากสี", R.drawable.cookie4, "Cookie"),
        Product("คุกกี้แอนครีม", 20.0, "คุกกี้แอนครีม", R.drawable.cookie5, "Cookie"),
        Product("จินเจอร์เบรด", 35.0, "จินเจอร์เบรด", R.drawable.gingerbreadman, "Cookie"),
        Product("เค้กสตอเบอร์รี่", 40.0, "เค้กสตอเบอร์รี่", R.drawable.cake1, "Cake"),
        Product("ชีสเค้ก", 40.0, "ชีสเค้ก", R.drawable.cake2, "Cake"),
        Product("เค้กช็อคโกแลต", 40.0, "เค้กช็อคโกแลต", R.drawable.cake3, "Cake"),
        Product("คัปเค้ก", 35.0, "คัปเค้ก", R.drawable.cupcake, "Cake"),
        Product("ช็อคโกแลตไส้คาราเมล", 20.0, "ช็อคโกแลตไส้คาราเมล", R.drawable.chocolate, "Chocolate"),
        Product("ช็อคโกแลตกล่องหัวใจ", 200.0, "ช็อคโกแลตกล่องหัวใจ", R.drawable.chocolatebox, "Chocolate"),
        Product("ช็อคโกแลตนูลเทล่า", 75.0, "ช็อคโกแลตนูลเทล่า", R.drawable.cholatenull, "Chocolate")
    )

    private var filteredProducts = allProducts.toMutableList()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllProductsBinding.inflate(inflater, container, false)


        productAdapter = ProductAdapter(requireContext(), filteredProducts)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = productAdapter


        val categories = listOf("All") + allProducts.map { it.category }.distinct()
        val categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            filterProductsByCategory(selectedCategory)
        }
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter

        return binding.root
    }


    private fun filterProductsByCategory(category: String) {
        filteredProducts = if (category == "All") {
            allProducts.toMutableList()
        } else {
            allProducts.filter { it.category == category }.toMutableList()
        }
        productAdapter.updateList(filteredProducts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
