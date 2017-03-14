

$(document).ready(function () {
    getBookList();

})

function getBookList() {
    $('#table').bootstrapTable({
        url: '/data/member/getBookList',
        columns: [{
            field: 'id',
            title: '订单号',
            align: 'center',
        },{
            field: 'roomImg',
            title: '',
            align: 'center',
            formatter:imgFormatter
        }, {
            field: 'hostelName',
            title: '客栈名',
            align: 'center',
            formatter: operateFormatter,
        }, {
            field: 'roomName',
            title: '房间类型',
            align: 'center'
        },{
            field: 'roomPrice',
            title: '价格',
            align: 'center'
        },{
            field: 'liveInDate',
            title: '入住时间',
            align: 'center'
        },{
            field: 'createDate',
            title: '下单时间',
            align: 'center'
        }],
    });
}
/*
 private int id;
 private String liveInDate;
 private String createDate;
 private int hostelId;
 private int vipId;
 private int roomId;
 private boolean valid;

 private String hostelName;
 private String hostelAddress;
 private String roomImg;
 private String roomName;
 private double roomPrice;
 */

function operateFormatter(value, row, index) {
    return [
        '<a href="/hostel/rooms?hostelId=',
        row.hostelId,
        '">',
        value,
        '</a>'
    ].join('');
}