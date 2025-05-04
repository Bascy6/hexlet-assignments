@import exercise.dto.users.UsersPage
@param UsersPage page

<h1>Users</h1>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    @for(var user : page.getUsers())
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getFirstName()} ${user.getLastName()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <a href="/users/${user.getId()}" class="btn btn-primary">View</a>
            </td>
        </tr>
    @endfor
    </tbody>
</table>