<%@include file="../include/_init.jsp"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css"> 
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>


<table id="items-table">
	<thead>
		<tr>
			<td>Name</td>
			<td>Share / weight</td>
			<td>ExpirDate</td>
			<td>PurchaseDate</td>
			<td>Link</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item.food.name}</td>
				<td>${item.share != null ? item.share : item.weight}</td>
				<td>${item.expirationDate}</td>
				<td>${item.purchaseDate}</td>
				<td>${item.id}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script>
$(document).ready( function () {
    $('#items-table').DataTable();
});

</script>