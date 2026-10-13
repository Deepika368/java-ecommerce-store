package com.deepu.ecommerce.controller;

import com.deepu.ecommerce.model.CartItem;
import com.deepu.ecommerce.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StoreController {
    private final List<Product> products = List.of(
        new Product(1, "Classic Cotton T-Shirt", "T-Shirts", 499, "Soft everyday cotton T-shirt.", "👕"),
        new Product(2, "Urban Denim Jeans", "Jeans", 1299, "Comfortable blue denim jeans.", "👖"),
        new Product(3, "Casual Checked Shirt", "Shirts", 899, "A versatile checked shirt for daily wear.", "👔"),
        new Product(4, "Floral Summer Dress", "Dresses", 1499, "Lightweight floral dress for sunny days.", "👗"),
        new Product(5, "Running Sneakers", "Footwear", 1799, "Lightweight sneakers for everyday use.", "👟"),
        new Product(6, "Minimal Backpack", "Accessories", 999, "A simple backpack for work or college.", "🎒"),
        new Product(7, "Oversized Graphic Tee", "T-Shirts", 699, "Relaxed fit graphic T-shirt.", "👕"),
        new Product(8, "Black Slim-Fit Jeans", "Jeans", 1399, "Classic black slim-fit denim.", "👖")
    );

    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        return cart(session).values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "") String q,
                       @RequestParam(defaultValue = "All") String category,
                       Model model) {
        List<Product> filtered = products.stream()
            .filter(p -> q.isBlank() || p.getName().toLowerCase().contains(q.toLowerCase())
                    || p.getCategory().toLowerCase().contains(q.toLowerCase()))
            .filter(p -> category.equals("All") || p.getCategory().equals(category))
            .collect(Collectors.toList());
        model.addAttribute("products", filtered);
        model.addAttribute("q", q);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("categories", products.stream().map(Product::getCategory).distinct().toList());
        return "index";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        Product product = findProduct(id);
        if (product != null) {
            Map<Integer, CartItem> cart = cart(session);
            if (cart.containsKey(id)) cart.get(id).increase();
            else cart.put(id, new CartItem(product));
        }
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        List<CartItem> items = new ArrayList<>(cart(session).values());
        model.addAttribute("items", items);
        model.addAttribute("total", items.stream().mapToDouble(CartItem::getSubtotal).sum());
        return "cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable int id, HttpSession session) {
        cart(session).remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/decrease/{id}")
    public String decrease(@PathVariable int id, HttpSession session) {
        CartItem item = cart(session).get(id);
        if (item != null) item.decrease();
        return "redirect:/cart";
    }

    @PostMapping("/cart/increase/{id}")
    public String increase(@PathVariable int id, HttpSession session) {
        CartItem item = cart(session).get(id);
        if (item != null) item.increase();
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        List<CartItem> items = new ArrayList<>(cart(session).values());
        if (items.isEmpty()) return "redirect:/cart";
        model.addAttribute("items", items);
        model.addAttribute("total", items.stream().mapToDouble(CartItem::getSubtotal).sum());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String customerName,
                             @RequestParam String email,
                             @RequestParam String address,
                             HttpSession session, Model model) {
        List<CartItem> items = new ArrayList<>(cart(session).values());
        if (items.isEmpty()) return "redirect:/cart";
        double total = items.stream().mapToDouble(CartItem::getSubtotal).sum();
        model.addAttribute("customerName", customerName);
        model.addAttribute("total", total);
        model.addAttribute("orderNumber", "ORD-" + (10000 + (int)(Math.random() * 90000)));
        cart(session).clear();
        return "success";
    }

    private Product findProduct(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> cart(HttpSession session) {
        Object existing = session.getAttribute("cart");
        if (existing == null) {
            Map<Integer, CartItem> newCart = new LinkedHashMap<>();
            session.setAttribute("cart", newCart);
            return newCart;
        }
        return (Map<Integer, CartItem>) existing;
    }
}
