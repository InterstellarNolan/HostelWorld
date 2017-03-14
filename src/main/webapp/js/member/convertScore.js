$(document).ready(function () {
   init();
});


function init() {
    $.ajax({
       url:'/data/member/getInfo',
        success:function (data) {
            var score=data.score;
            var moneyLeft=data.moneyLeft;
            $('#scoreOriginal').html(score);
            $('#moneyLeft').html(moneyLeft);
        }
    });
    $.ajax({
        url:'/constants/RATE_SCORE_TO_MONEY',
        success:function (data) {
            var rate=data;
            var rule='钱数 = 积分数 * '+rate;
            $('#scoreToMoneyRule').html(rule);
        }
    })
}
$('#converseBtn').click(function () {
    var scoreToConvert=$('#score').val();
    $.ajax({
        type:'POST',
        url:'/vip/convert',
        data:{score:scoreToConvert},
        success:function (msg) {
            $('#message').html(msg);
            location.reload();
        }


    })
})
