var preLoader = document.getElementById("pre_loader");

function fadeOutnojquery(el) {
    el.style.opacity = 1;
    var intervalLoader = setInterval(function () {
        el.style.opacity = el.style.opacity - 0.05;
        if (el.style.opacity <= 0.05) {
            clearInterval(intervalLoader);
            preLoader.style.display = "none";
        }
    }, 16);
}

window.onload = function () {
    setTimeout(function () {
        fadeOutnojquery(preLoader);
    }, 200);
};