/**
 * Created by Jonathan on 5/5/2017.
 */
$("#GoButooon").click(function () {
    var searchString = $("#searchBox").val().trim();
    location.href="search?searchStr="+searchString;
});