
$(document).ready(function () {
    getPayList();
})

function getPayList() {
    $('#table').bootstrapTable({
        url: '/data/member/getPayList',
        columns: [{
            field: 'createDate',
            title: '时间',
            align: 'center'
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
            title: '房间价格',
            align: 'center'
        },{
            field: 'money',
            title: '实付价格',
            align: 'center',
        }],
    });
}

function operateFormatter(value, row, index) {
    return [
        '<a href="/hostel/rooms?hostelId=',
        row.hostelId,
        '">',
        value,
        '</a>'
    ].join('');
}