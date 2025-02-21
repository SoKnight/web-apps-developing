document.querySelectorAll('input[required],select[required],textarea[required]').forEach(input => {
    const label = document.querySelector(`label[for="${input.id}"]`);
    if (label) {
        label.classList.add('required');
    }
});

let productSelect = document.getElementById("product");
if (productSelect != null) {
    productSelect.addEventListener("change", function () {
        let quantityInput = document.getElementById("quantity");
        quantityInput.value = "1";

        const selectedProductId = this.value;
        const url = new URL(window.location.href);
        url.searchParams.set('id', selectedProductId);
        window.location.href = url.toString();
    });
}

function decreaseQuantity() {
    let quantityInput = document.getElementById("quantity");
    let currentValue = parseInt(quantityInput.value);
    let minValue = parseInt(quantityInput.min);

    if (currentValue > minValue) {
        quantityInput.value = currentValue - 1;
        updateOrderSum(currentValue - 1)
    }
}

function increaseQuantity() {
    let quantityInput = document.getElementById("quantity");
    let currentValue = parseInt(quantityInput.value);
    let maxValue = parseInt(quantityInput.max);

    if (currentValue < maxValue) {
        quantityInput.value = currentValue + 1;
        updateOrderSum(currentValue + 1)
    }
}

function updateOrderSum(count) {
    let basePriceSpan = document.getElementById("base_price");
    let basePrice = parseInt(basePriceSpan.textContent);

    let orderSumSpan = document.getElementById("order_sum");
    orderSumSpan.textContent = `${basePrice * count}`;
}
