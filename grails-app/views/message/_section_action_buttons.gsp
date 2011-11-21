<ul class="section-actions-buttons button-list">
	<g:if test="${messageSection != 'poll'}">
        <li><g:remoteLink class="btn" controller="quickMessage" action="create" onSuccess="launchMediumWizard('Quick Message', data, 'Send', true);" id="quick_message" class="section-action-button btn">
			Quick message
		</g:remoteLink></li>
	</g:if>
	<g:if test="${messageSection == 'trash' && messageInstanceTotal != 0}">
		<li>
			<select id="trash-actions" onchange="launchEmptyTrashConfirmation();">
				<option value="na" class="na">Trash actions...</option>
				<option id="empty-trash" value="empty-trash" >Empty trash</option>
			</select>
		</li>
	</g:if>
	<g:if test="${messageSection == 'announcement' || messageSection == 'folder'}">
		<g:if test="${!params.viewingArchive}">
			<li>
				<g:link class="btn" controller="$messageSection" action="archive" id="${ownerInstance?.id}">Archive ${messageSection}</g:link>
			</li>
		</g:if>
		<g:else>
			<li>
				<g:link class="btn" controller="$messageSection" action="unarchive" id="${ownerInstance?.id}">Unarchive ${messageSection}</g:link>
			</li>
		</g:else>
		<li>
			<g:select name="more-actions" class="more-actions" from="${['Export', 'Delete ' + messageSection]}"
					keys="${['export', 'delete']}"
					noSelection="${['': 'More actions...']}"/>
		</li>
	</g:if>
	<g:if test="${!(messageSection in ['trash', 'poll', 'folder', 'announcement'])}">
		<li><g:link elementId="export" url="#" class="btn">
			Export
		</g:link></li>
	</g:if>
</ul>