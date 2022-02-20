<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Task Tracker</title>
</head>
<body>
<form action="/create" method="post">

    <table>
        <thead>All Projects</thead>
        <tr>
            <th>Name</th>
            <th>Start Date</th>
            <th>Completion Date</th>
            <th>Status</th>
            <th>Priority</th>
            <th></th>
        </tr>

        <#list projects as p>
            <tr>
                <th><a href="/project/${p.id}">${p.name}</a></th>
                <th>${p.startDate}</th>
                <th>${p.completionDate}</th>
                <th>${p.status.toString()}</th>
                <th>${p.priority}</th>
            </tr>
        </#list>
        <tr>
            <th><label for="name"></label><input autofocus id="name" name="name" required></th>
            <th><label for="startDate"></label><input autofocus id="startDate" name="startDate" type="datetime-local"
                                                      required>
            </th>
            <th><label for="completionDate"></label><input autofocus id="completionDate" name="completionDate"
                                                           type="datetime-local" required></th>
            <th><label for="status"></label><select autofocus required id="status" name="status">
                    <option selected="selected" value="ACTIVE">active</option>
                    <option value="COMPLETED">completed</option>
                    <option value="NOT_STARTED">not started</option>
                </select></th>
            <th><label for="priority"></label><input required autofocus id="priority" name="priority" type="number">
            </th>
        </tr>
        <tr>
            <th><input type="submit" value="Add new"></th>
        </tr>
    </table>
</form>

</body>