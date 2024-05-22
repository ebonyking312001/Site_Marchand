
<header class="p-3  border-bottom  text-bg-dark text-white">
	<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<ul
				class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
				<li class="d-flex d-row align-items-center"><svg
						xmlns="http://www.w3.org/2000/svg" height="20" width="22.5"
						viewBox="0 0 576 512">
						<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
						<path fill="#ffffff"
							d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z" /></svg>

					<a href="JspAccueil" class="nav-link px-2 text-white fw-bold ">Accueil</a>
				</li>

			</ul>

			<form
				class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 d-flex d-row px-3"
				role="search" action="CtrlChercherArticles" method="get">
				<input type="text" name="inputData" type="search"
					class="form-control rounded-0" placeholder="Chercher..."
					aria-label="Search">
				<button type="submit"
					class="btn btn-sm btn-secondary text-white rounded-0">
					<svg xmlns="http://www.w3.org/2000/svg" height="20" width="20"
						viewBox="0 0 512 512">
						<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
						<path fill="#ffffff"
							d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" /></svg>
				</button>
			</form>
<style>
/* Optional: Style the dropdown icon */
.dropdown-toggle {
  display: inline-block;
  padding: 0.5rem;
}

.dropdown-toggle svg {
  vertical-align: middle;
  fill: #ffffff;
}

.dropdown-menu {
  min-width: auto; /* Adjust the width of the dropdown menu */
}

.dropdown-item {
  color: #000; /* Adjust text color */
}

.dropdown-item:hover {
  background-color: #f8f9fa; /* Hover effect */
}</style>
			<a style="text-decoration: none;" href="ServletPanier"
				class=" text-white "> <svg xmlns="http://www.w3.org/2000/svg"
					height="30" width="32.5" viewBox="0 0 576 512">
					<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
					<path fill="#ffffff"
						d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z" /></svg>
				<span class="badge badge-light" id="intNbArtCard">${countArtCard}</span></a>
			</a> <a href="ServletListeCourse" class="text-white ms-3"> <svg
					xmlns="http://www.w3.org/2000/svg" height="30" width="32.5"
					viewBox="0 0 24 24">
                    <path fill="#ffffff"
						d="M3 13h2v-2H3v2zm0 4h2v-2H3v2zm0-8h2V7H3v2zm4 8h12v-2H7v2zm0-4h12v-2H7v2zm0-6v2h12V7H7z" />
                </svg>
			</a>
		<!-- Example single danger button -->
<div class="btn-group mx-2">
  <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    Account
  </button>
  <ul class="dropdown-menu ">
      <c:choose>
      <c:when test="${not empty sessionScope.user}">
        <li><a class="dropdown-item" href="Logout">Sign Out</a></li>
      </c:when>
      <c:otherwise>
        <li><a class="dropdown-item" href="CtrlAuthentification">Sign In</a></li>
      </c:otherwise>
    </c:choose>
  </ul>
</div>

		</div>
	</div>
</header>
