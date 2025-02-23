document.querySelectorAll('input[required],select[required],textarea[required]').forEach(input => {
    const label = document.querySelector(`label[for="${input.id}"]`);
    if (label) {
        label.classList.add('required');
    }
});