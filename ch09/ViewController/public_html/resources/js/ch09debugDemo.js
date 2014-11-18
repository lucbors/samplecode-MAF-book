function showMessage(){
pltfrm = device.platform;
if (pltfrm=="iOS"){
      alert("This is running on iOS");
} else
{
  alert("This is running on "+ pltfrm);
}
}