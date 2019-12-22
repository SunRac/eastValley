$(window).on('scroll', function () {
  var scroll = $(window).scrollTop();

  if (scroll >= 50) {
    $(".sticky").addClass("stickyadd");
  } else {
    $(".sticky").removeClass("stickyadd");
  }
});

$('.nav-item a').on('click', function (event) {
  var anchor = $(this).attr("href");
  if(anchor.startsWith("http")){
    return;
  }
  $('html, body').stop().animate({
    scrollTop: $(anchor).offset().top - 70
  }, 1300, 'easeInOutExpo');
  event.preventDefault();
});

$(document).on('click', '.navbar-collapse.show', function (e) {
  if ($(e.target).is('a')) {
    $(this).collapse('hide');
  }
});
var hasConsole = typeof console !== "undefined"

var fingerprintReport = function () {
  var d1 = new Date()
  Fingerprint2.get(function (components) {
    var murmur = Fingerprint2.x64hash128(components.map(function (pair) { return pair.value }).join(), 31)
    var d2 = new Date();
    var time = d2 - d1;
    document.querySelector("#fp-time").textContent = time;
    document.querySelector("#fp-value").textContent = murmur;
    var details = "";
    if (hasConsole) {
      console.log("time", time);
      console.log("fingerprint hash", murmur);
    }
    for (var index in components) {
      var obj = components[index];
      var line = obj.key + " = " + String(obj.value).substr(0, 100);
      if (hasConsole) { console.log(line); }
    }
    $("#fp-results").removeClass("invisible");
  })
}

$(function () {
  $(".gist-meta").hide();
  $("#btn-get-fp").on("click", function () { fingerprintReport() });
  $("#contact-form").on("submit", function (e) {
    e.preventDefault();
    var data = {
      email: $("#contact-email").val(),
      name: $("#contact-name").val(),
      comments: $("#contact-comments").val()
    };
    var errorMessage = "Error submitting the data, try again later";
    $.ajax({
      url: $(e.target).attr("action"),
      method: "POST",
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(data),
      success: function (resp) {
        if (resp.status === 202) {
          alert("Thank you, we received your data");
          $(e.target).get(0).reset();
        } else { alert(errorMessage); }
      },
      error: function (xhr, error) {
        alert(errorMessage);
      }
    });
  });
});