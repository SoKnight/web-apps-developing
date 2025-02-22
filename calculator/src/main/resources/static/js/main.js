// заполнение селекторов оснований систем счисления
document.querySelectorAll("select.base-selector").forEach(selector => {
    for (let i = 2; i <= 16; i++) {
        selector.innerHTML += `<option value="${i}">${i}</option>`;
    }
})

// клавиатура из кнопок 0-9 + A-F
const buttonsGrid = document.getElementById("buttons-grid");
for (let int = 0; int < 16; int++) {
    const button = document.createElement("button");
    const token = int.toString(16).toUpperCase();

    button.textContent = token;
    button.onclick = function () { appendToken(token) }
    buttonsGrid.appendChild(button);
}

// кнопка перевода
const convertButton = document.getElementById("convert-button");

// поля ввода/вывода
const inputField = document.getElementById("src-number");
const outputField = document.getElementById("result");

// селекторы систем счисления
const fromBase = document.getElementById("from-base");
const toBase = document.getElementById("to-base");
updateKeyboardAvailability();

// обновление доступности кнопок клавиатуры при изменении системы счисления
fromBase.onchange = function () {
    clearForm();
    updateKeyboardAvailability();
};

// --- функции

function appendToken(token) {
    inputField.value += token;
}

function updateKeyboardAvailability() {
    const base = parseInt(fromBase.value, 10);
    document.querySelectorAll(".buttons-grid button").forEach(button => {
        button.disabled = parseInt(button.textContent, 16) >= base;
    });
}

function performConvertation() {
    if (!inputField.value)
        return;

    const from = parseInt(fromBase.value, 10);
    const to = parseInt(toBase.value, 10);
    console.log(`Converting '${inputField.value}' from '${from}' to '${to}'...`)

    if (from === to) {
        outputField.value = inputField.value;
        console.log("Same target base, the input value is result!");
        return;
    }

    try {
        const asDecimal = parseInt(inputField.value, from);
        if (isNaN(asDecimal)) {
            alert("Некорректное число для данной системы счисления!");
            return;
        }

        const converted = asDecimal.toString(to).toUpperCase();
        outputField.value = converted;
        console.log(`Result is '${converted}'`)
    } catch (err) {
        alert("Произошла ошибка при конвертации!");
    }
}

function clearForm() {
    inputField.value = "";
    outputField.value = "";
}