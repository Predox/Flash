from playwright.sync_api import sync_playwright, expect
import time

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page(
        base_url='http://localhost:8080/'
    )
    page.goto('index.html')
    page.fill('#email', 'pedro@example.com')
    print("Inserindo Email.")
    page.fill('#senha', '1234')
    print("Inserindo Senha.")
    page.click('button[type="submit"]')
    print("Fazendo Login")
    try:
        expect(page).to_have_url('http://localhost:8080/home.html')
        print("Login feito com sucesso!")
    except Exception:
        mensagem = page.locator('#mensagemLogin')
        try:
            if mensagem.text_content() == "Email ou senha inválidos.":
                print("Login Incorreto")
            else:
                print(f"Mensagem inesperada: {mensagem.text_content()}")
        except:
            print("Login falhou, mas sem mensagem clara de erro")



    
   