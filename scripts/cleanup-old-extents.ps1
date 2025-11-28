Param(
    [string]$ReportsDir = "test-output/ExtentReports",
    [int]$MaxFiles = 10
)

if (-not (Test-Path $ReportsDir)) {
    Write-Error "Reports directory '$ReportsDir' not found."
    exit 1
}

$files = Get-ChildItem -Path $ReportsDir -Filter "ExtentReport_*.html" | Sort-Object LastWriteTime -Descending

if ($files.Count -lt $MaxFiles) {
    Write-Host "Extent report count: $($files.Count) (< $MaxFiles). No cleanup needed."
    exit 0
}

Write-Host "Extent report count: $($files.Count) (>= $MaxFiles). Cleaning up old files..."

$filesToDelete = $files | Select-Object -Skip $MaxFiles

foreach ($file in $filesToDelete) {
    Write-Host "Deleting: $($file.Name)"
    Remove-Item -Path $file.FullName -Force
}

Write-Host "Cleanup complete. Kept $MaxFiles newest reports."
exit 0
