document.querySelectorAll('.product').forEach(item => {
    let input = item.querySelector('input');
    let minusBtn = item.querySelector('.minus');
    let plusBtn = item.querySelector('.plus');

    minusBtn.addEventListener('click', function() {
        let currentValue = parseInt(input.value);
        if (currentValue > 1) {
            input.value = currentValue - 1;
        }
    });

    plusBtn.addEventListener('click', function() {
        let currentValue = parseInt(input.value);
        input.value = currentValue + 1;
    });
});