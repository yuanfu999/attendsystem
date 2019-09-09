
function init_page() {
    $("#li_end").text(page_total)
    if(page_total > 6){
        var li_arr = $(".pagination li");
        for(var i=0; i<li_arr.length; i++){
            var id = li_arr[i].firstChild.id;
            if(id != "more_1"){
                $("#" + id).removeAttr("hidden");
            }
        }
        $("#li_end").text(page_total);
    }else if(page_total == 6){
        var li_arr = $(".pagination li");
        for(var i=0; i<li_arr.length; i++){
            var id = li_arr[i].firstChild.id;
            if(id != "more_1"){
                $("#" + id).removeAttr("hidden");
            }
            if(id == "more_2"){
                $("#" + id).attr("hidden", "hidden");
            }
        }
        $("#li_end").text(page_total);
    }else{
        var li_arr = $(".pagination li");
        for(var i=0; i<li_arr.length; i++){
            var id = li_arr[i].firstChild.id;
            if(id != "more_1"){
                $("#" + id).removeAttr("hidden");
            }
            if(id == "li_" + page_total){
                break;
            }
        }
    }
}

//隐藏分页
function hide_page() {
    var li_arr = $(".pagination li");
    for(var i=0; i<li_arr.length; i++){
        var id = li_arr[i].firstChild.id;
        // if(id != "more_1"){
            $("#" + id).attr("hidden", "hidden");
        // }
    }
    for (var i=1; i<=5; i++){
        $("#li_" + i).text(i)
    }
    $("#li_" + i).text(page_total)
}

//页面跳转
function jump_page(obj){
    var first_index = parseInt($("#li_1").text());
    var last_index = parseInt($("#li_end").text());
    if(obj.id == 'pre'){
        if(now_page_index <= 1){
            now_page_index = 1;
            return;
        }else{
            -- now_page_index;
        }
        var li_2_index = parseInt($("#li_2").text());
        if(now_page_index >= 5 && page_total > 6 && first_index < li_2_index){
            var index;
            for(var i=2; i<=5; i++){
                if(now_page_index == 5){
                    $("#li_" + i).text(i);
                    $("#more_1").attr("hidden", "hidden");
                }else{
                    index = now_page_index + i - 5;
                    $("#li_" + i).text(index);
                }
            }
            if(index - 4 == first_index){
                $("#more_1").attr("hidden", "hidden");
            }
            if(index == last_index - 1){
                $("#more_2").attr("hidden", "hidden");
            }else{
                $("#more_2").removeAttr("hidden");
            }
        }else if(now_page_index < 5 && page_total > 6 && first_index < li_2_index){
            var index;
            for(var i=2; i<=5; i++){
                $("#li_" + i).text(i);
            }
            $("#more_1").attr("hidden", "hidden");
            $("#more_2").removeAttr("hidden");
        }
        $("#now_page").text(now_page_index);

        getEmpByPage(now_page_index);
    }else if(obj.id == 'next'){
        if(now_page_index >= page_total){
            now_page_index = page_total;
            return;
        }else{
            ++ now_page_index;
        }

        var li_5_index = $("#li_5").text();
        if(now_page_index > 5 && page_total > 6 && last_index-1 > li_5_index){
            var index;
            for(var i=2; i<=5; i++){
                index = i + 3 + now_page_index - 8;
                $("#li_" + i).text(index);
            }
            if(index == page_total - 1){
                $("#more_2").attr("hidden", "hidden");
            }else{
                $("#more_2").removeAttr("hidden");
            }

        }
        $("#now_page").text(now_page_index);
        getEmpByPage(now_page_index);
    }else{
        var index = $("#" + obj.id).text()
        now_page_index = parseInt(index);
        $("#now_page").text(index)
        if(index == 1){
            if (page_total > 6){
                $("#more_1").attr("hidden", "hidden");
                $("#more_2").removeAttr("hidden");
                for(var i=2; i<=5; i++){
                    $("#li_" + i).text(i)
                }
            }else if(page_total < 5){
                $("#more_1").attr("hidden", "hidden");
                $("#more_2").attr("hidden","hidden");
                for(var i=2; i<=5; i++){
                    $("#li_" + i).text(i)
                }
            }

        }

        if(index == page_total){
            if (page_total > 6){
                $("#more_2").attr("hidden", "hidden");
                for(var i=2; i<=5; i++){
                    $("#li_" + i).text(page_total + i - 6);
                }
            }else{
                $("#more_2").attr("hidden", "hidden");
                for(var i=2; i<=5; i++){
                    $("#li_" + i).text(page_total + i - 6);
                }
                $("#more_1").attr("hidden", "hidden");
            }

        }


        if(page_total <= 5){
            $("#more_2").attr("hidden", "hidden");
            $("#more_1").attr("hidden", "hidden");
            for(var i=1; i<=page_total; i++){
                $("#li_" + i).text(i);
            }
            $("#li_end").attr("hidden", "hidden");
        }
        getEmpByPage(index);
    }
    if(now_page_index > 5 && page_total > 6){
        $("#more_1").removeAttr("hidden");
        if(page_total - now_page_index > 4){
            $("#more_2").removeAttr("hidden");
        }
    }

}

//清除表格
function clear_table() {
    var table = document.getElementById("emp_table")
    var rows = table.rows.length;
    var cols = table.rows[0].cells.length;
    for (var i=1; i<rows; i++){
        var cells = table.rows[i].cells;
        for (var j=0; j<cols; j++){
            table.rows[i].cells[j].innerText = "";
        }

    }
}

//获取输入跳转的界面，并跳转界面
function get_jump_page() {
    var page = $("#jump_page_input").val();

    if(page > page_total || page < 1){
        alert("请输入正确的跳转页面!");
    }else{
        $("#now_page").text(page);
        getEmpByPage(page);
        $("#jump_page_input").val("")
    }
}