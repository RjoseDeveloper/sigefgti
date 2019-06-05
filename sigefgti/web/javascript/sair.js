 function sair() {
                if (confirm("Tem certeza que pretende Terminar a Sessao?")) {
                    
                    window.location = "home.jsp";
                    return true;
                    
                } else {
                    return false;
                }
            }