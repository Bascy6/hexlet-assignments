@import exercise.dto.users.UserPage
@param UserPage page

<h1>User Details</h1>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h5>
        <p class="card-text">
            <strong>ID:</strong> ${page.getUser().getId()}<br>
            <strong>First Name:</strong> ${page.getUser().getFirstName()}<br>
            <strong>Last Name:</strong> ${page.getUser().getLastName()}<br>
            <strong>Email:</strong> ${page.getUser().getEmail()}
        </p>
        <a href="/users" class="btn btn-primary">Back to users list</a>
    </div>
</div>