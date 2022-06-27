function calendar(id, year, month) {
    var Dlast = new Date(year, month + 1, 0).getDate(),
        D = new Date(year, month, Dlast),
        DNlast = new Date(D.getFullYear(), D.getMonth(), Dlast).getDay(),
        DNfirst = new Date(D.getFullYear(), D.getMonth(), 1).getDay(),
        calendar = '<tr>',
        month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    if (DNfirst != 0) {
        for (var i = 1; i < DNfirst; i++) calendar += '<td>';
    } else {
        for (var i = 0; i < 6; i++) calendar += '<td>';
    }
    for (var i = 1; i <= Dlast; i++) {
        var mounth = D.getMonth() +1;
        if(i<10){var stringTodayDate = "0"+i +"."+mounth +"."+D.getFullYear();}
        if(D.getMonth()<10){var stringTodayDate = i +".0"+mounth+"."+D.getFullYear();}
        else{var stringTodayDate = i +".0"+ mounth +"."+D.getFullYear();}
        if (i == new Date().getDate() && D.getFullYear() == new Date().getFullYear() && D.getMonth() == new Date().getMonth()) {
            calendar += '<td class="today"><button name="daySelection" type="submit" id="stringTodayDate" style="color: #FF9A8D;" class="DeleteUserFromGroup" value="'+stringTodayDate+'">' + i;
        } else {
            calendar += '<td><button name="daySelection" type="submit" id="stringTodayDate" style="color: #4A538B;" class="DeleteUserFromGroup" value="'+stringTodayDate+'">' + i;
        }
        if (new Date(D.getFullYear(), D.getMonth(), i).getDay() == 0) {
            calendar += '<tr><button name="daySelection" type="submit" id="stringTodayDate" style="color: #4A538B;" class="DeleteUserFromGroup" value="">';
        }
    }
    for (var i = DNlast; i < 7; i++) calendar += '</button></td> ';
    document.querySelector('#' + id + ' tbody').innerHTML = calendar;
    document.querySelector('#' + id + ' thead td:nth-child(2)').innerHTML = month[D.getMonth()] + ' ' + D.getFullYear();
    document.querySelector('#' + id + ' thead td:nth-child(2)').dataset.month = D.getMonth();
    document.querySelector('#' + id + ' thead td:nth-child(2)').dataset.year = D.getFullYear();
    if (document.querySelectorAll('#' + id + ' tbody tr').length < 6) {
        document.querySelector('#' + id + ' tbody').innerHTML += '<tr><td> <td> <td> <td> <td> <td> <td> ';
    }
}

calendar("calendar", new Date().getFullYear(), new Date().getMonth());
document.querySelector('#calendar thead tr:nth-child(1) td:nth-child(1)').onclick = function () {
    calendar("calendar", document.querySelector('#calendar thead td:nth-child(2)').dataset.year, parseFloat(document.querySelector('#calendar thead td:nth-child(2)').dataset.month) - 1);
}
document.querySelector('#calendar thead tr:nth-child(1) td:nth-child(3)').onclick = function () {
    calendar("calendar", document.querySelector('#calendar thead td:nth-child(2)').dataset.year, parseFloat(document.querySelector('#calendar thead td:nth-child(2)').dataset.month) + 1);
}
