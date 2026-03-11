# Problem: ShoppingCart.java – OOP‑style Shopping Cart System
## Problem Statement (LLD / OOP‑style)

Design an in‑memory shopping cart system that:

Lets a User add and remove Products to/from a cart.

Each product has an ID, name, and price.

The cart keeps track of quantity per product.

Supports:

adding a product (or incrementing its quantity),

removing a product (or decrementing its quantity),

getting the full cart (product → quantity),

computing the total price of the cart.

You must use strong OOP principles:

Encapsulation: internal cart state is private.

Abstraction: ShoppingCart interface hides how it stores products and quantities.

Composition: the cart “owns” product data indirectly via ID → quantity maps.
