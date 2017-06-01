<%
    ui.decorateWith("appui", "standardEmrPage")
    ui.includeCss("reportingui", "reportsapp/home.css")

    def appFrameworkService = context.getService(context.loadClass("org.openmrs.module.appframework.service.AppFrameworkService"))
    def overview = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ugandaemr.reports.overview")
    def monthly = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ugandaemr.reports.monthly")
    def registers = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ugandaemr.reports.registers")
    def quarterly = appFrameworkService.getExtensionsForCurrentUser("org.openmrs.module.ugandaemr.reports.quarterly")

    def contextModel = [:]
%>

<script type="text/javascript">
    var breadcrumbs = [
        {icon: "icon-home", link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm'},
        {
            label: "${ ui.message("reportingui.reportsapp.home.title") }", link: "${ ui.pageLink("ugandaemrreports",
        "reportsHome")
}"
        }
    ];
</script>
<h2>UgandaEMR Reports</h2>
<div class="dashboard clear">
    <div class="info-container column">
        <% if (overview) { %>
        <div class="info-section">
            <div class="info-header"><h3>Facility Reports</h3></div>

            <div class="info-body">
                <ul>
                    <% overview.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>

        <% if (registers) { %>
        <div class="info-section">
            <div class="info-header"><h3>Registers</h3></div>

            <div class="info-body">
                <ul>
                    <% registers.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>

    <div class="info-container column">
        <% if (monthly) { %>
        <div class="info-section">
            <div class="info-header"><h3>Monthly Reports</h3></div>

            <div class="info-body">
                <ul>
                    <% monthly.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>

        <% if (quarterly) { %>
        <div class="info-section">
            <div class="info-header"><h3>Quarterly Reports</h3></div>

            <div class="info-section">
                <ul>
                    <% quarterly.each { %>
                    <li>
                        ${ui.includeFragment("uicommons", "extension", [extension: it, contextModel: contextModel])}
                    </li>
                    <% } %>
                </ul>
            </div>
        </div>
        <% } %>
    </div>
</div>