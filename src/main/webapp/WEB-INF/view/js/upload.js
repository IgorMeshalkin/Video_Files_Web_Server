const selectButton = document.getElementById('selectButton')
const submitButton = document.getElementById('submitButton');

submitButton.addEventListener('click', () => {
    selectButton.style.opacity = '.5';
    selectButton.style.pointerEvents = 'none';
});