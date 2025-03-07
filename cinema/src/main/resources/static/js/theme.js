document.addEventListener("DOMContentLoaded", function() {
    // Verifica se o cookie já contém a escolha de tema
    let theme = getCookie("theme");
    if (theme) {
        document.body.classList.add(theme);
    } else {
        // Se não houver cookie, aplica o tema claro por padrão
        document.body.classList.add("claro");
    }

    // Alterna entre claro e escuro quando o botão for clicado
    document.getElementById("toggle-theme").addEventListener("click", function() {
        let currentTheme = document.body.classList.contains("claro") ? "claro" : "escuro";
        let newTheme = currentTheme === "claro" ? "escuro" : "claro";
        
        document.body.classList.remove(currentTheme);
        document.body.classList.add(newTheme);

        // Salva a escolha do tema no cookie
        setCookie("theme", newTheme, 365);
    });
});

// Função para definir o cookie
function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

// Função para obter o valor do cookie
function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

