<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script id="paginateTemplate" type="x-tmpl-mustache">
<div class="col-xs-6">
    <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">
        总共 {{maxPageNo}} 页, 第 {{from}} ~ {{to}} 条数据
    </div>
</div>
    
<div class="col-xs-6">
    <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
        <ul class="pagination">
            <li class="paginate_button previous {{^firstUrl}}disabled{{/firstUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="1" data-url="{{firstUrl}}" class="page-action">首页</a>
            </li>
            <li class="paginate_button {{^beforeUrl}}disabled{{/beforeUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{beforePageNo}}" data-url="{{beforeUrl}}" class="page-action">前一页</a>
            </li>
            <li class="paginate_button active" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-id="{{pageNo}}" >第{{pageNo}}页</a>
                <input type="hidden" class="pageNo" value="{{pageNo}}" />
            </li>
            <li class="paginate_button {{^nextUrl}}disabled{{/nextUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{nextPageNo}}" data-url="{{nextUrl}}" class="page-action">后一页</a>
            </li>
            <li class="paginate_button next {{^lastUrl}}disabled{{/lastUrl}}" aria-controls="dynamic-table" tabindex="0">
                <a href="#" data-target="{{maxPageNo}}" data-url="{{lastUrl}}" class="page-action">尾页</a>
            </li>
        </ul>
    </div>
</div>
</script>

<script type="text/javascript">
    var paginateTemplate = $("#paginateTemplate").html();
    Mustache.parse(paginateTemplate);

    function renderPage(pageInfo, url, idElement, callback) {
        var paramStartChar = url.indexOf("?") > 0 ? "&" : "?";
        var from = (pageInfo.pageNum - 1) * pageInfo.pageSize + 1;
        var view = {
            from: from > pageInfo.total ? pageInfo.total : from,
            to: (from + pageInfo.pageSize - 1) > pageInfo.total ? pageInfo.total : (from + pageInfo.pageSize - 1),
            total: pageInfo.total,
            pageNo: pageInfo.pageNum,
            maxPageNo: pageInfo.pages,
            nextPageNo: pageInfo.hasNextPage ? pageInfo.nextPage : pageInfo.pageNum,
            beforePageNo: pageInfo.hasPreviousPage ? pageInfo.prePage : pageInfo.pageNum,
            firstUrl:pageInfo.isFirstPage ? '' : url + paramStartChar + "pageNum=1&pageSize=" + pageInfo.pageSize,
            beforeUrl: pageInfo.hasPreviousPage ? url + paramStartChar + "pageNum=" + pageInfo.prePage + "&pageSize=" + pageInfo.pageSize : '',
            nextUrl: pageInfo.hasNextPage ? (url + paramStartChar + "pageNum=" + pageInfo.nextPage + "&pageSize=" + pageInfo.pageSize) : '',
            lastUrl: pageInfo.isLastPage ? '' : (url + paramStartChar + "pageNum=" + pageInfo.pages + "&pageSize=" + pageInfo.pageSize)
        };

        $("#" + idElement).html(Mustache.render(paginateTemplate, view));

        $(".page-action").click(function (e) {
            e.preventDefault();
            $("#" + idElement + " .pageNo").val($(this).attr("data-target"));
            var targetUrl = $(this).attr("data-url");
            if (targetUrl != '') {
                $.ajax({
                    url: targetUrl,
                    success: function (result) {
                        if (callback) {
                            callback(result, url);
                        }
                    }
                })
            }
        })
    }
</script>
